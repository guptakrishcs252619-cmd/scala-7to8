import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object p15 {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("cancer.csv"))
    val data = reader.allWithHeaders().take(10)   // Only first 5 rows
    reader.close()

    val radius = DenseVector(
      data.map(row => row("Radius (mean)").toDouble).toArray
    )

    val x = DenseVector((1 to radius.length).map(_.toDouble).toArray)

    val fig = Figure("Cancer Line Plot")
    val plt = fig.subplot(0)

    plt += plot(x, radius)

    plt.xlabel = "Sample"
    plt.ylabel = "Radius (mean)"
    plt.title = "First 5 Cancer Samples"

    fig.refresh()
  }
}