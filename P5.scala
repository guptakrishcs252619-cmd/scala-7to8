import breeze.linalg._
import breeze.stats.distributions.Uniform

object P5 {
  def main(args: Array[String]): Unit = {

    // Generate a random 3x3 matrix
    val matrix = DenseMatrix.rand[Double](3, 3, Uniform(0, 10))

    println("Original Matrix:")
    println(matrix)

    // Transpose
    val transpose = matrix.t
    println("\nTranspose of Matrix:")
    println(transpose)

    // Determinant
    val determinant = det(matrix)
    println("\nDeterminant:")
    println(determinant)
  }
}
