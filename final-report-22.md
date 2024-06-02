# CS304 final report
## 1.Metric
### Lines of Code
**Total lines of code:**

5212 (backend)

1808779 (frontend)

1814211 (total)

**Tool used:**
`cloc`
### Number of Packages/Modules
**Total number of packages/modules:**

12 (backend)

7 (frontend)

23 (total)

**Tool used:**
using `(Get-ChildItem -Recurse -Directory .\cs304\src\main\java).Count` and manual count from project structure
### Number of Source Files
**Total number of source files:**

113 (backend)

32 (frontend)

**Tool used:**
using `(Get-ChildItem -Recurse -Include *.java, *.py .\cs304\src).Count` and `(Get-ChildItem -Recurse -Include *.js, *.vue, *.html, *.css .\frontend\cs304\src).Count`
### Number of Dependencies
**Total number of dependencies:**

24 (backend)

17 (frontend)

41 (total)

**Tool used:**
manual count via `package.json` for front-end and `pom.xml` for back-end
## 2. Documentation
### Documentation for End Users

**Location:** [End User Documentation](https://github.com//sustech-cs304/team-project-24spring-22/blob/master/README.md)
### Documentation for Developers

**Location:** [Developer API Documentation](http://120.77.79.53:8090/swagger-ui/index.html)
## 3. Tests
### Automated Testing
**Tools/Frameworks Used:**

JUnit + Spring Test + GitHub Actions
- **JUnit:** For writing and running unit tests.
- **Spring Test:** For integration testing of Spring applications.
- **GitHub Actions:** For continuous integration and automated test execution.

**Source Code/Artifacts:**

[Back-end Tests](https://github.com/sustech-cs304/team-project-24spring-22/tree/master/cs304/src/test/java/com/example/cs304)

**Effectiveness of Tests:**

- **test coverage report:** 
[test result]()
![](./snapshots/img_1.png)
![](./snapshots/img_2.png)
![](./snapshots/img_3.png)
- **Test Coverage:** 83%
- **Explanation:** The test coverage report indicates that 83% of the codebase is covered by tests, ensuring that key functionalities are verified. The detailed breakdown shows:
    - **Instructions Coverage:** 83% of all instructions are tested.
    - **Branch Coverage:** 51% of all branches are tested.
    
    
    The report demonstrates good coverage of the main components of the application, particularly in the entity, common, controller and service.impl packages, which have coverage rates of 82%, 81%, 90% and 93%, respectively. 
### Manual Testing (if applicable)
**Reasons for Manual Testing:** N/A 

**Manual Testing Steps:** N/A

## 4.Build
### Build Process

#### **Tools/Frameworks Used:**

- **GitHub Action**

#### **Tasks Executed in Build:**

We have integrated automated building and deployment, with front-end and back-end building and deployment divided into two parts.

##### FrontEnd Steps

1. Checkout repository
2. Set up Node.js
3. Install dependencies
4. Build
5. Build docker image
6. delete old docker image
7. delete old docker tar
8.  upload new docker image and start-up script
9. load new docker image
10. run project

##### BackEnd Steps

1. Set up the working directory
2. Set up JDK 18
3. packge
4. Build docker image
5. delete old docker image
6. delete old docker tar
7.  upload new docker image and start-up script
8. load new docker image
9. run project

### Final Artifacts

**Description:** The build produces executable files, documentation.

### Buildfile/Script

**Location**:

[Back-end Buildfile (pom.xml)]()

[Front-end Build Script (webpack.config.js)]()

## 5. Deployment (2 points)

### Containerization

#### Tools/Frameworks Used
- **Docker**
- **Github Action**

#### Script/Artifacts
- **FrontEnd Dockerfile**: 

```dockerfile
FROM nginx
RUN rm /etc/nginx/conf.d/default.conf
ADD default.conf /etc/nginx/conf.d/
COPY dist/ /usr/share/nginx/html/
```

- **BackEnd Dockerfile**: 

```dockerfile
FROM ubuntu:22.04
RUN apt-get update && apt-get install -y openjdk-18-jdk
RUN apt-get update && apt-get install -y python3 python3-pip
RUN pip3 install pymysql openai
RUN mkdir /app
COPY ai.py /app/ai.py
COPY target/cs304-0.0.1-SNAPSHOT.jar /app/back.jar
WORKDIR /app
CMD ["java", "-jar", "back.jar"]
```

- **FrontEnd Github Action Script**

```yml
name: Vue Deploy

on:
  push:
    branches:
      - master
    paths:
      - 'frontend/**'

jobs:
  build-and-deploy:
    runs-on: ubuntu-22.04

    steps:
      - name: Checkout repository
        uses: actions/checkout@v2

      - name: Set up Node.js
        uses: actions/setup-node@v2
        with:
          node-version: '17.9.1'

      - name: Install dependencies
        run: |
          cd ./frontend/cs304
          npm install

      - name: Build
        run: |
          cd ./frontend/cs304
          npm run build
          
      - name: Build docker image
        run: |       
          cd ./frontend/cs304                  # run：该步骤要执行的命令，| 代表可以有多条命令
      
          docker build -t front .   # 构建docker镜像，命令最后的点代表Dockerfile所在目录
          docker save -o front.tar front # 保存压缩后的docker镜像，这一步是为了方便后面我们将该镜像上传至指定的服务器
          ls

      - name: delete old docker image
        run: |
          cd ./frontend/cs304
          sshpass -p ${{secrets.REMOTE_SERVER01_PWD}} ssh -o StrictHostKeyChecking=no root@${{secrets.REMOTE_SERVER01_IP}} "docker stop front || true && docker rm front || true && docker rmi front || true"
      
            # 删除旧的docker镜像压缩包
      - name: delete old docker tar
        run: |
          cd ./frontend/cs304
          sshpass -p ${{secrets.REMOTE_SERVER01_PWD}} ssh -o StrictHostKeyChecking=no root@${{secrets.REMOTE_SERVER01_IP}} "cd ${{secrets.REMOTE_PROJECT_CATALOGUE}} && rm -f front.tar"
            # 上传新的docker镜像
      - name: upload new docker image and start-up script
        run: |
          cd ./frontend/cs304
          sshpass -p ${{secrets.REMOTE_SERVER01_PWD}} scp -r -o StrictHostKeyChecking=no ./front.tar root@${{secrets.REMOTE_SERVER01_IP}}:${{secrets.REMOTE_PROJECT_CATALOGUE}}
            # 加载tar文件，加载docker镜像
      - name: load new docker image
        run: sshpass -p ${{secrets.REMOTE_SERVER01_PWD}} ssh -o StrictHostKeyChecking=no root@${{secrets.REMOTE_SERVER01_IP}} "docker load -i ${{secrets.REMOTE_PROJECT_CATALOGUE}}/front.tar"
            # docker run 运行，启动容器
      - name: run project
        run: sshpass -p ${{secrets.REMOTE_SERVER01_PWD}} ssh -o StrictHostKeyChecking=no root@${{secrets.REMOTE_SERVER01_IP}} "docker run -d -p 8080:8080 --name front front"
```

- **BackEnd Github Action Script**

  ```yml
  name: Java CI with Maven
  
  on:
    push:
      branches: [ "master" ] # 当向master分支进行git push时触发该工作流
      paths:
        - 'cs304/**'
    pull_request:
      branches: [ "master" ] # 当向master分支合并分支时触发该工作流
      paths:
        - 'cs304/**'
  # 工作流具体执行的任务
  jobs:
    build:
      runs-on: ubuntu-22.04
      steps:
      - name: pull latest code
        uses: actions/checkout@v3 # 使用官方的checkout action，用于将仓库中的最新代码检查并拉取到工作目录中
      - name: ls
        run: |
          ls
      - name: Set up the working directory
        run: |
          cd ./cs304
      - name: Set up JDK 18
        uses: actions/setup-java@v3 # 安装java环境的action
        with:
          java-version: '18' # 指定jkd版本
          distribution: 'temurin'
          cache: maven # 使用maven缓存，避免每次构建项目时都要重新下载maven依赖
      - name: packge
        run: |
          cd ./cs304
          mvn clean
          mvn package -DskipTests -Dmaven.compile.skip=true
      - name: Build docker image
        run: |       
          cd ./cs304                  # run：该步骤要执行的命令，| 代表可以有多条命令
          
          docker build -t back .   # 构建docker镜像，命令最后的点代表Dockerfile所在目录
          docker save -o back.tar back # 保存压缩后的docker镜像，这一步是为了方便后面我们将该镜像上传至指定的服务器
          ls
         # 删除旧的docker镜像
         # 使用sshpass以非交互式的用户名密码方式登录远程服务器
         # 停止运行中的docker容器、删除该容器、删除镜像
         # docker命令后面的两条竖线 || 代表当前面的docker命令执行失败时，命令可以继续往下执行，这样做的原因是在第一次构建项目的时候肯定不会存在这些容器和镜像，此时删除或停止这些容器就会失败
      - name: delete old docker image
        run: |
          cd ./cs304
          sshpass -p ${{secrets.REMOTE_SERVER01_PWD}} ssh -o StrictHostKeyChecking=no root@${{secrets.REMOTE_SERVER01_IP}} "docker stop back || true && docker rm back || true && docker rmi back || true"
  
            # 删除旧的docker镜像压缩包
      - name: delete old docker tar
        run: |
          cd ./cs304
          sshpass -p ${{secrets.REMOTE_SERVER01_PWD}} ssh -o StrictHostKeyChecking=no root@${{secrets.REMOTE_SERVER01_IP}} "cd ${{secrets.REMOTE_PROJECT_CATALOGUE}} && rm -f back.tar"
            # 上传新的docker镜像
      - name: upload new docker image and start-up script
        run: |
          cd ./cs304
          sshpass -p ${{secrets.REMOTE_SERVER01_PWD}} scp -r -o StrictHostKeyChecking=no ./back.tar root@${{secrets.REMOTE_SERVER01_IP}}:${{secrets.REMOTE_PROJECT_CATALOGUE}}
            # 加载tar文件，加载docker镜像
      - name: load new docker image
        run: sshpass -p ${{secrets.REMOTE_SERVER01_PWD}} ssh -o StrictHostKeyChecking=no root@${{secrets.REMOTE_SERVER01_IP}} "docker load -i ${{secrets.REMOTE_PROJECT_CATALOGUE}}/back.tar"
            # docker run 运行，启动容器
      - name: run project
        run: sshpass -p ${{secrets.REMOTE_SERVER01_PWD}} ssh -o StrictHostKeyChecking=no root@${{secrets.REMOTE_SERVER01_IP}} "docker run -d -p 8090:8090 --name back back"
  
  ```

  

#### Proof of Successful Containerization

- **Snapshot**:
  ![Docker Build Success Snapshot](pic/container.png)
