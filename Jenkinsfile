pipeline{
   agent any


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