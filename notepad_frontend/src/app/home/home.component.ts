import {Component, OnInit} from '@angular/core';
import {NoteService} from './shared/note.service';
import {Note} from './shared/note.model';
import {MatDialog} from '@angular/material';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ModalAddNodeComponent} from './components/modal-add-node/modal-add-node.component';
import {ModalConfirmationComponent} from './components/modal-confiramtion/modal-confirmation.component';
import {FormControl} from '@angular/forms';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  selected = new FormControl(0);
  notes: Note[] = [];
  isLoading = true;
  tempUuid = 'noUuid';

  constructor(private noteService: NoteService, private dialog: MatDialog, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.loadNotes();
  }

  private loadNotes() {
    this.noteService.getNotes()
      .subscribe(data => {
        this.notes = data;
        this.isLoading = false;
      }, err => {
        this.isLoading = false;
        this.showSnackBar(err.message);
      });
  }

  onDeleteNote(noteToDelete: Note) {
    const dialogRef = this.dialog.open(ModalConfirmationComponent, {
      data: {name: noteToDelete.name}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'yes') {
        if (noteToDelete.uuid !== this.tempUuid) {
          this.noteService.deleteNote(noteToDelete.name)
            .subscribe(_ => this.clearData(noteToDelete));
        } else {
          this.clearData(noteToDelete);
        }
      }
      if (this.notes.length === 0) {
        this.addTempData('tempFile.txt');
      }
    });
  }

  onSaveNote(noteToSave: Note) {
    if (noteToSave.content === undefined || noteToSave.content.length === 0) {
      this.showSnackBar('Please, provide some content');
      return;
    }
    if (noteToSave.uuid === this.tempUuid) {
      this.noteService.saveNote(noteToSave)
        .subscribe(_ => this.updateDataContent(noteToSave), err => this.showSnackBar(err.message));
    } else {
      this.noteService.updateNote(noteToSave.name, noteToSave.content)
        .subscribe(_ => this.updateDataContent(noteToSave), err => this.showSnackBar(err.message));
    }
  }

  onAddNewNote() {
    const dialogRef = this.dialog.open(ModalAddNodeComponent, {
      data: {name: ''}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined && result !== 'no' && result.length !== 0) {
        if (!result.endsWith('.txt')) {
          result = result + '.txt';
        }
        this.addTempData(result);
        this.selected.setValue(this.notes.length - 1);
      }
    });
  }

  private showSnackBar(message: string) {
    this.snackBar.open(message, 'Ok');
  }

  private updateDataContent(noteToUpdate: Note) {
    this.notes.forEach(note => {
      if (note.uuid === noteToUpdate.uuid) {
        note.content = noteToUpdate.content;
      }
    });
  }

  private clearData(noteToDelete: Note) {
    this.notes = this.notes.filter(note => note.uuid !== noteToDelete.uuid);
  }

  private addTempData(name: string) {
    this.notes.push({uuid: this.tempUuid, content: '', name});
  }
}
