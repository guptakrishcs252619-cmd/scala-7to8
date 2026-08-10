import com.github.tototoshi.csv._
import java.io.File
import scala.util.Try
import org.knowm.xchart.{XYChartBuilder, SwingWrapper}

object p6 {

  def sigmoid(z: Double): Double = {
    1.0 / (1.0 + math.exp(-z))
  }

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("krishdata.csv"))
    val rows = reader.allWithHeaders()
    reader.close()

    // Read valid rows
    val validRows = rows.filter { row =>
      Try(row("Price_Lakhs").trim.toDouble).isSuccess &&
        Try(row("Sales_FY2024").trim.toDouble).isSuccess
    }

    val x = validRows.map(_.apply("Price_Lakhs").trim.toDouble).toArray

    // Binary Labels
    val y = validRows.map { row =>
      if (row("Sales_FY2024").trim.toDouble >= 100000)
        1.0
      else
        0.0
    }.toArray

    var b0 = 0.0
    var b1 = 0.0

    val learningRate = 0.001
    val epochs = 5000

    // Training
    for (_ <- 1 to epochs) {

      var db0 = 0.0
      var db1 = 0.0

      for (i <- x.indices) {

        val prediction = sigmoid(b0 + b1 * x(i))
        val error = prediction - y(i)

        db0 += error
        db1 += error * x(i)
      }

      b0 -= learningRate * db0 / x.length
      b1 -= learningRate * db1 / x.length
    }

    println("Logistic Regression Model")
    println("----------------------------")
    println("Intercept : " + b0)
    println("Slope     : " + b1)

    // Prediction
    val newPrice = 15.0
    val probability = sigmoid(b0 + b1 * newPrice)

    println("\nPrediction for Price = 15 Lakhs")
    println("Probability = " + probability)

    if (probability >= 0.5)
      println("Predicted Class = 1 (High Sales)")
    else
      println("Predicted Class = 0 (Low Sales)")

    // Graph Data
    val minX = x.min
    val maxX = x.max

    val graphX = (0 to 100).map(i =>
      minX + i * (maxX - minX) / 100.0
    ).toArray

    val graphY = graphX.map(v => sigmoid(b0 + b1 * v))

    val chart = new XYChartBuilder()
      .width(800)
      .height(600)
      .title("Logistic Regression")
      .xAxisTitle("Price_Lakhs")
      .yAxisTitle("Probability")
      .build()

    chart.addSeries("Sigmoid Curve", graphX, graphY)

    new SwingWrapper(chart).displayChart()
  }
}