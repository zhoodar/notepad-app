import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {ModalData} from '../../shared/note.model';

@Component({
  selector: 'app-modal-add-node',
  templateUrl: './modal-add-node.component.html',
  styleUrls: ['./modal-add-node.component.scss']
})
export class ModalAddNodeComponent {
  validated = false;

  constructor(private dialogRef: MatDialogRef<ModalAddNodeComponent>,
              @Inject(MAT_DIALOG_DATA) public data: ModalData) {
  }

  onCancel() {
    this.dialogRef.close('no');
  }

  onCreate(value: string) {
    if (value !== undefined && value.length > 0) {
      this.dialogRef.close(value);
    }
    this.validated = true;
  }
}
