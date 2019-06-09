import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Note, NoteContent} from './note.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const apiUrl = 'http://localhost:8080/v1/notes';
@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private http: HttpClient) {
  }

  getNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(apiUrl);
  }

  deleteNote(name: string) {
    const url = `${apiUrl}/${name}`;
    return this.http.delete(url, httpOptions);
  }

  updateNote(name, content: string): Observable<Note> {
    const url = `${apiUrl}/${name}`;
    const note = new NoteContent();
    note.content = content;

    return this.http.put<Note>(url, note, httpOptions);
  }

  saveNote(product: Note): Observable<Note> {
    return this.http.post<Note>(apiUrl, product, httpOptions);
  }
}
