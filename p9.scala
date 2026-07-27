import com.github.tototoshi.csv._
import java.io.File
import scala.util.Try

object p9 {

  def main(args: Array[String]): Unit = {

    val inputFile = new File("dataset.csv")

    println("CSV Path : " + inputFile.getAbsolutePath)
    println("File Exists : " + inputFile.exists())

    val reader = CSVReader.open(inputFile)

    val allRows = reader.allWithHeaders()
    reader.close()

    // Numeric columns in your dataset
    val numericColumns = Seq("Age", "Avg_Quantity_g")

    // Calculate mean and missing values
    val stats = numericColumns.map { col =>

      val values = allRows.map(row => row.getOrElse(col, "").trim)

      val validNumbers = values.flatMap(v => Try(v.toDouble).toOption)

      val missingCount = values.count(v => v.isEmpty || Try(v.toDouble).isFailure)

      val mean =
        if (validNumbers.nonEmpty)
          validNumbers.sum / validNumbers.size
        else
          0.0

      (col, (mean, missingCount))

    }.toMap

    println("\n========== Missing Data Report ==========")

    stats.foreach {
      case (col, (mean, missing)) =>
        println(f"$col%-20s Missing = $missing%-3d Mean = $mean%.2f")
    }

    // Replace missing values with mean
    val cleanedRows = allRows.map { row =>

      numericColumns.foldLeft(row) { (updatedRow, col) =>

        val value = updatedRow.getOrElse(col, "").trim

        val newValue =
          if (value.isEmpty || Try(value.toDouble).isFailure)
            f"${stats(col)._1}%.2f"
          else
            value

        updatedRow.updated(col, newValue)
      }

    }

    // Save cleaned dataset
    val writer = CSVWriter.open(new File("dataset_cleaned.csv"))

    val headers = cleanedRows.head.keys.toSeq

    writer.writeRow(headers)

    cleanedRows.foreach(row => writer.writeRow(headers.map(h => row(h))))

    writer.close()

    println("\nCleaning completed successfully.")
    println("New file created : dataset_cleaned.csv")
  }
}