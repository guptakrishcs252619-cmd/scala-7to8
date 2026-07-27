import com.github.tototoshi.csv._
import java.io.File

object p12 {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("dataset.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    println("Column Names:")
    println(data.head.keys.mkString(", "))
  }
}