import com.github.tototoshi.csv._
import java.io.File
import scala.util.Try

object p4 {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("krishdata.csv"))
    val rows = reader.allWithHeaders()
    reader.close()

    // Sort safely even if Sales_FY2024 is empty
    val sortedRows = rows.sortBy { row =>
      Try(row("Sales_FY2024").trim.toDouble).getOrElse(0.0)
    }.reverse

    val top5 = sortedRows.take(5)

    println("Top 5 Cars by Sales_FY2024")
    println("---------------------------------------------------------------")
    println("Brand\t\tCar Name\t\tSales")

    top5.foreach { row =>
      println(s"${row("Brand")}\t\t${row("Car_Name")}\t\t${row("Sales_FY2024")}")
    }
  }
}