from pyspark.sql import SparkSession

spark = SparkSession.builder \
    .appName("JoinCSVFiles") \
    .master("local[*]") \
    .getOrCreate()

df1 = spark.read.csv(
    "student.csv",
    header=True,
    inferSchema=True
)

df2 = spark.read.csv(
    "student2.csv",
    header=True,
    inferSchema=True
)

print("Students Data:")
df1.show()

print("Student Details:")
df2.show()

result = df1.join(
    df2,
    on="Name",
    how="inner"
)

print("Joined Data:")
result.show()

result.write \
    .mode("overwrite") \
    .option("header", True) \
    .csv("joined_output")

spark.stop()