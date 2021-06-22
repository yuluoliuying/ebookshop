import axios from 'axios'
import {Message, MessageBox} from 'element-ui'
import store from '../store'
import {getToken} from '@/utils/auth'

// 创建axios实例
const service = axios.create({
  baseURL: "http://114.116.226.207:8222/", // api 的 base_url
  timeout: 20000 // 请求超时时间
})

