import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object p13 {

  def main(args: Array[String]): Unit = {

    // Read CSV file
    val reader = CSVReader.open(new File("dataset.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Read Age column
    val age = DenseVector(
      data.map(row => row("Age").toDouble).toArray
    )

    // Read Avg_Quantity_g column
    val quantity = DenseVector(
      data.map(row => row("Avg_Quantity_g").toDouble).toArray
    )

    // Create Figure
    val fig = Figure("Scatter Plot")
    val plt = fig.subplot(0)

    // Scatter Plot (Breeze 2.1.0)
    plt += scatter(age, quantity, (_: Int) => 3.0)

    // Labels
    plt.xlabel = "Age"
    plt.ylabel = "Average Quantity (g)"
    plt.title = "Age vs Average Quantity"

    // Show Plot
    fig.refresh()
  }
}