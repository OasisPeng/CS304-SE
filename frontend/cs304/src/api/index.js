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
export const getDian = data => {
  return request.get('/dianzan/course/'+data.id);
}
//打分
export const saveFen = data => {
  return request.post('/dafen',data);
}
export const deleteFen = data => {
  return request.delete('/dafen',{data});
}
export const getFen = data => {
  return request.get('/dafen/course/'+data.id);
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
export const addGpt= data => {
  const { user, id,content } = data;
  return request.post(`/ai/chat`,data);
}
export const getGptList= data => {
  return request.get('/ai/user/'+data.user);
}
export const byIDGpt= data => {
  return request.get('/ai/id/'+data.id);
}
export const deleteGpt= data => {
  return request.delete('/ai/id/'+data.id);
}