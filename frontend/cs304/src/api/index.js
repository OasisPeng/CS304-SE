import request from '@/utils/request';

// 课程列表
export const getCourseData = data => {
  return request.post(`/course/listPage`, data);
};

export const saveEvent = data => {
  return request.post('/event/save',data);
}
// 课程详情
// 点赞
export const saveZan = data => {
  return request.post('/dianzan',data);
}
export const deleteZan = data => {
  return request.delete('/dianzan',{data});
}
//打分
export const saveFen = data => {
  return request.post('/dafen',data);
}
export const deleteFen = data => {
  return request.delete('/dafen',{data});
}
// 评论
export const saveContent = data => {
  return request.post('/pinglun',data);
}
export const deleteContent = data => {
  return request.delete('/pinglun',{data});
}
export const getContentList = id => {
  return request.get(`/pinglun/course/${id}`);
}
export const Login = data => {
  return request.post('/login/login',data);
}
export const getGpt= data => {
  return request.get('/ai/chat',data);
}
