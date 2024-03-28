import request from '@/utils/request';

// 课程列表
export const getCourseData = data => {
  return request.post(`/course/listPage`, data);
};

export const saveEvent = data => {
  return request.post('/event/save',data);
}