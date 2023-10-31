pipeline{
   agent any
   environment {
           PATH = "C:\\Program Files (x86)\\sbt\\bin"  // Adjust the path to your sbt installation
       }

   stages{
        stage('Build'){
            steps{
                echo "Package Application"
                bat 'scripts\\PackageApplication.bat'
                  echo "Packaging Done"
            }
        }
   }
}