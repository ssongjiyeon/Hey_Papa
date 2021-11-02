import $axios from 'axios'
const baseUrl = 'https://k5b206.p.ssafy.io/api'

export function getCarecenter ({}) {
  const url = baseUrl + '/Carecenter'
  return $axios.get(url)
}