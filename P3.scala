import scala.util.Random
import math.sqrt

object P3 {
  def main(args: Array[String]): Unit = {

    // Generate 10 random numbers between 1 and 100
    val data = List.fill(10)(Random.nextInt(100) + 1)

    // Calculate Mean
    val mean = data.sum.toDouble / data.length

    // Calculate Variance
    val variance = data.map(x => math.pow(x - mean, 2)).sum / data.length

    // Calculate Standard Deviation
    val stdDev = sqrt(variance)

    // Display Results
    println("Random Dataset : " + data)
    println("Mean           : " + mean)
    println("Variance       : " + variance)
    println("Standard Deviation : " + stdDev)
  }
}
