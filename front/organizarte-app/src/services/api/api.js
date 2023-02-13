import HttpClient from './httpClient';

class Api {
  client;
  constructor() {
    this.client = new HttpClient();
  }

  crearHogar = (body) => this.client.post(`hogar/`, body);
  crearEspacio = (body) => this.client.put(`hogar/espacio/`, body);
  getUserByName = (nombre) => this.client.get(`integrante/${nombre}`);
  getEspacios = (hogarId) => this.client.get(`hogar/espacios?hogarId=${hogarId}`);
  getIntegrantes = (hogarId) => this.client.get(`hogar/integrantes?hogarId=${hogarId}`);
  addIntegrante = (body) => this.client.put(`hogar/integrante`, body);
  getTareasByUserId = (userId) => this.client.get(`tareas?integranteId=${userId}`);
  repartir = (userId, hogarId) => this.client.post(`tareas/reparto?integranteId=${userId}&hogarId=${hogarId}`);
  realizarTarea = (userId, hogarId, tareaId) => this.
                  client.post(`tareas?integranteId=${userId}&hogarId=${hogarId}&tareaId=${tareaId}`)
}

export default Api;
