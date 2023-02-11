import Axios from 'axios';
import Config from '../config/config';

class HttpClient {
  axiosClient;

  constructor() {
    this.axiosClient = Axios.create({
      baseURL: Config.API_HOST,
      timeout: 10000
    });
  }

  get = async (url) => {
    const { data } = await this.axiosClient.get(url);
    return data;
  };

  post = async (url, body) => {
    const { data } = await this.axiosClient.post(url, body);
    return data;
  };

  put = async (url, body) => {
    const { data } = await this.axiosClient.put(url, body);
    return data;

  };
}

export default HttpClient;