import breeze.linalg._
import breeze.stats.mean

object P4 {
  def main(args: Array[String]): Unit = {

    // Create two dense vectors
    val v1 = DenseVector(1.0, 2.0, 3.0, 4.0, 5.0)
    val v2 = DenseVector(5.0, 4.0, 3.0, 2.0, 1.0)

    // Sum of vector
    val sum = breeze.linalg.sum(v1)

    // Mean of vector
    val avg = mean(v1)

    // Dot Product
    val dot = v1 dot v2

    // Output
    println("Vector 1 : " + v1)
    println("Vector 2 : " + v2)
    println("Sum      : " + sum)
    println("Mean     : " + avg)
    println("Dot Product : " + dot)
  }
}
