import { Injectable } from '@angular/core';
import { ApiService } from '../api/ApiService';

@Injectable()
export class RecentTacosService {

  constructor(private apiService: ApiService) {
  }

  getRecentTacos() {
    return this.apiService.get('/design/recent');
  }

}
