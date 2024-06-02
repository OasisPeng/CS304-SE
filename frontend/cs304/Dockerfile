FROM nginx

# 删除默认的配置文件
RUN rm /etc/nginx/conf.d/default.conf
# 添加你的配置文件到镜像中
ADD default.conf /etc/nginx/conf.d/
# 将dist文件夹下的内容复制到镜像的/usr/share/nginx/html/目录下
COPY dist/ /usr/share/nginx/html/