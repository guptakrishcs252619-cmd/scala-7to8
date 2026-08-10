import com.github.tototoshi.csv._
import breeze.linalg._
import java.io.File

object p5 {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("krishdata(1).csv"))
    val rows = reader.allWithHeaders()
    reader.close()

    // Independent Variable (Price)
    val x = DenseVector(
      rows.map(r => r("Price_Lakhs").toDouble).toArray
    )

    // Dependent Variable (Sales)
    val y = DenseVector(
      rows.map(r => r("Sales_FY2024").toDouble).toArray
    )

    // Design Matrix
    val X = DenseMatrix.horzcat(
      DenseMatrix.ones[Double](x.length, 1),
      x.toDenseMatrix.t
    )

    // Linear Regression
    val beta = inv(X.t * X) * X.t * y

    println("Intercept = " + beta(0))
    println("Slope = " + beta(1))

    // Prediction for Price = 15 Lakhs
    val newCar = DenseVector(1.0, 15.0)
    val predictedSales = newCar.dot(beta)

    println("Predicted Sales for Price 15 Lakhs = " + predictedSales)
  }
}