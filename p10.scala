import com.github.tototoshi.csv.*

import java.io.File

object p10 {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("dataset.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Print all column names
    println("Columns:")
    println(data.head.keys.mkString(", "))

    println("\nSearching for Maharashtra...\n")

    // Change "State" if your column has a different name
    val maharashtraRows = data.filter { row =>
      row.get("State")
        .exists(_.trim.equalsIgnoreCase("Maharashtra"))
    }

    println(s"Total people from Maharashtra: ${maharashtraRows.length}")

    maharashtraRows.foreach(row => println(row.values.mkString(", ")))
  }
}

