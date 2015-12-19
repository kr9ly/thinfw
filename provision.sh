#!/usr/bin/env bash

localectl set-locale LANG=ja_JP.utf8
localectl set-keymap jp106
timedatectl set-timezone Asia/Tokyo
yum -y update
wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u60-b27/jdk-8u60-linux-x64.rpm"
rpm -ivh jdk-8u60-linux-x64.rpm

cat << "EOF" > /etc/yum.repos.d/MariaDB.repo
# MariaDB 10.1 CentOS repository list - created 2015-09-14 12:57 UTC
# http://mariadb.org/mariadb/repositories/
[mariadb]
name = MariaDB
baseurl = http://yum.mariadb.org/10.1/centos7-amd64
gpgkey=https://yum.mariadb.org/RPM-GPG-KEY-MariaDB
gpgcheck=1
EOF

yum install -y MariaDB-server MariaDB-client
systemctl enable mysql.service
systemctl start mysql.service

systemctl disable firewalld
systemctl stop firewalld
