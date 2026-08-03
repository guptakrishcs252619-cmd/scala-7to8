import com.github.tototoshi.csv._
import java.io.File

object p3 {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("krishdata.csv"))

    // Read Brand column
    val data = reader.allWithHeaders().map(_("Brand"))

    reader.close()

    // Frequency Distribution
    val frequency = data.groupBy(identity).map {
      case (brand, list) => (brand, list.size)
    }

    // Sort by Brand name
    val sortedFrequency = frequency.toSeq.sortBy(_._1)

    println("Frequency Distribution and Cumulative Frequency")
    println("-----------------------------------------------------------")
    println(f"${"Brand"}%-20s ${"Frequency"}%-10s ${"Cumulative"}")

    var cumulative = 0

    for ((brand, freq) <- sortedFrequency) {
      cumulative += freq
      println(f"$brand%-20s $freq%-10d $cumulative")
    }
  }
}