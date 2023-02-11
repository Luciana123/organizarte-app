import HttpClient from './httpClient';

class Api {
  client;
  constructor() {
    this.client = new HttpClient();
  }
  getUserByName = (nombre) => this.client.get(`integrante/?nombre=${nombre}`);
  
}

export default Api;
