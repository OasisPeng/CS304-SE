import axios from 'axios';
const baseURL = '/api' ;//代理路径，vue.config.js
// const baseURL = '120.77.79.53:8090' ;//代理路径，vue.config.js

// 默认带上cookie
axios.defaults.withCredentials = true;
const request = axios.create({
    baseURL, // 基准的 URL 路径
    // timeout: 3000, // 超时时间
});
// 请求拦截
request.interceptors.request.use(
    config => {
        // // 设置请求所带的token/session/'Content-Type'等
        const token= JSON.parse(localStorage.getItem('info'))?.token ?? ''
        console.log(token,66)
        // Object.assign(config.headers, { token });
        Object.assign(config.headers, { Authorization:`Bearer ${token}` });
        return config;
    },

    err => {
        // uniqueMessage.error('连接服务器失败，请稍后再试');
        return Promise.reject(err);
    }
);


request.interceptors.response.use(
    response => {
        const res = response.data;
        // 根据实际情况来设置
        /* ------------- */
        const resCode = res.code;
        const successCodes = [200, 201,400]; // 正常返回
        const reloginCodes = [401]; // 需要进入登录页
        /* ------------- */
        const token = response.headers.token;
        if (token) sessionStorage.setItem('cdfu-token', token);
        if (response.config.responseType === 'blob') {
            return response;
        }
        if (Object.prototype.hasOwnProperty.call(res, 'code')) {
            if (successCodes.includes(resCode)) {
                // 正常情况
                return res;
            } else if (reloginCodes.includes(resCode)) {

                return res;
            } else {
                // 其它报错
                return Promise.reject(res);
            }
        } else {
            // 不规范的接口以及其它外部接口直接正常处理
            return res;
        }
    },
    err => {
        // 某些状态只返回状态码不返回response
        const { status } = err?.response ?? '';
        const reloginCodes = [401]; // 需要进入登录页
        if (reloginCodes.includes(status)) {

            return Promise.reject(err);
        }
        // 取消请求正常返回
        if (err && err instanceof err.constructor && err.__CANCEL__) {
            return Promise.resolve(err);
        }
        // uniqueMessage.error('连接服务器失败，请稍后再试');
        return Promise.reject(err);
    }
);

export default request;
