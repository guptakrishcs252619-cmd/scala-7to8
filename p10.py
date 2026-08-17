from pyspark.sql import SparkSession

spark = SparkSession.builder \
    .appName("FilterRows_Krish085") \
    .master("local[*]") \
    .getOrCreate()

df = spark.read.csv(
    "studentg.csv",
    header=False,
    inferSchema=True
)

df = df.toDF("Name", "Subject", "Marks")

print("Original Data:")
df.show()

new_student = spark.createDataFrame(
    [("Krish 085", "Python", 85)],
    ["Name", "Subject", "Marks"]
)

df = df.union(new_student)

threshold = 75

filtered_df = df.filter(df["Marks"] > threshold)

print("Students with Marks greater than", threshold)
filtered_df.show()

spark.stop()