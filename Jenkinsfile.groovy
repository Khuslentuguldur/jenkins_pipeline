node {
    stage("Say Hello"){
      properties([parameters([text(defaultValue: '34.216.166.165', description: '''Dev : 34.216.166.165 Qa : 18.236.251.153 Prod : 52.38.233.129''', name: 'Remote_Instance')])])
    git 'https://github.com/Khuslentuguldur/jenkins_august.git'


    }
    stage("Install Apache"){
      sh "ssh ec2-user@${Remote_Instance} sudo yum install httpd -y"
    }
    stage("Create Index.html"){
      sh "scp index.html ec2-user@${Remote_Instance}:/tmp"
    }
    stage("Move Files"){
      sh "ssh ec2_user@${Remote_Instance} sudo mv /tmp/index.html /var/www/html/index.html"
    }
    stage("Restart httpd"){
      sh "ssh ec2_user@${Remote_Instance} sudp systemctl restart httpd"
    }
}
