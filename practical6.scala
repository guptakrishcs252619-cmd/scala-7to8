import breeze.linalg._

object practical6 {

  def main(args: Array[String]): Unit = {

    // Create a 4x4 matrix
    val matrix = DenseMatrix(
      (1, 2, 3, 4),
      (5, 6, 7, 8),
      (9, 10, 11, 12),
      (13, 14, 15, 16)
    )

    println("Original Matrix:")
    println(matrix)

    // Extract Sub-Matrix (Rows 1 to 2, Columns 1 to 3)
    val subMatrix = matrix(1 to 2, 1 to 3)

    println("\nSub Matrix:")
    println(subMatrix)

    // Row Sums
    val rowSums = sum(subMatrix(*, ::))
    println("\nRow Sums:")
    println(rowSums)

    // Column Sums
    val colSums = sum(subMatrix(::, *))
    println("\nColumn Sums:")
    println(colSums)
  }
}