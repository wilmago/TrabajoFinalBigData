
//  Algoritmo regresion lineal multiple, se quiere modelar la probabilidad de cada persona de obtener un préstamo de un banco. La probabilidad es un número entre 0 y 1 y es nuestra etiqueta

//Definición clase persona
case class Person(rating: String, income: Double, age: Int
//Inicializar Spark. SetMaster 
val sc = new SparkContext(new SparkConf().setAppName("People linear regression").setMaster("local")

//Preprocesamiento de datos
def prepareFeatures(people: Seq[Person]): Seq[org.apache.spark.mllib.linalg.Vector] = {  val maxIncome = people map(_ income) max  val maxAge = people map(_ age) max  people map (p =>    Vectors dense(      if (p.rating == "A") 0.7 else if (p.rating == "B") 0.5 else 0.3, 
 p.income / maxIncome,      p.age.toDouble / maxAge)) }

//Crear secuencia de numeros
def prepareFeaturesWithLabels(features: Seq[org.apache.spark.mllib.linalg.Vector]): Seq[LabeledPoint] =  (0d to 1 by (1d / features.length)) zip(features) map(l => LabeledPoint(l._1, l._2))

//Entrenamiento y predicción
val data = sc.parallelize(prepareFeaturesWithLabels(prepareFeatures(people))

//Definir el algoritmo
val algorithm = new LinearRegressionWithSGD() val model = algorithm run training val prediction = model predict(test map(_ features))

 //Análisis de resultados
 
 val predictionAndLabel = prediction zip(test map(_ label)) predictionAndLabel.foreach((result) => println(s"predicted label: ${result._1}, actual label: ${result._2}"))
