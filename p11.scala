import scala.io.Source

object p11 {

  def main(args: Array[String]): Unit = {

    // File name
    val fileName = "krish,txt.txt"

    // Read file
    val source = Source.fromFile(fileName)
    val text = source.getLines().mkString(" ")
    source.close()

    // Split text into words
    val words = text
      .toLowerCase()
      .split("\\W+")
      .filter(_.nonEmpty)

    // Count frequency of each word
    val wordCount = words.groupBy(identity).map {
      case (word, list) => (word, list.length)
    }

    // Display result
    println("\n===== WORD FREQUENCY COUNTER =====\n")

    wordCount.toSeq
      .sortBy(-_._2)
      .foreach {
        case (word, count) =>
          println(f"$word%-15s : $count")
      }
  }
}
