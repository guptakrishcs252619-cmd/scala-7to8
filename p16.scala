import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object p16 {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("cancer.csv"))
    val data = reader.allWithHeaders().take(10)
    reader.close()

    val y = DenseVector(
      data.map(row => row("Radius (mean)").toDouble).toArray
    )

    val x = DenseVector(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)

    val fig = Figure("Cancer Line Plot")
    val plt = fig.subplot(0)

    plt += plot(x, y)

    plt.xlabel = "Sample Number (1-5)"
    plt.ylabel = "Radius (mean)"
    plt.title = "Cancer Dataset Line Plot"

    fig.refresh()

    println("Sample 1 = " + data(0)("Id"))
    println("Sample 2 = " + data(1)("Id"))
    println("Sample 3 = " + data(2)("Id"))
    println("Sample 4 = " + data(3)("Id"))
    println("Sample 5 = " + data(4)("Id"))
  }
}