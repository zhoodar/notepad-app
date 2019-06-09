import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {ModalData} from '../../shared/note.model';

@Component({
  selector: 'app-modal-add-node',
  templateUrl: './modal-confirmation.component.html',
  styleUrls: ['./modal-confirmation.component.scss']
})
export class ModalConfirmationComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ModalConfirmationComponent>,
              @Inject(MAT_DIALOG_DATA) public data: ModalData) {
  }

  onCancel(): void {
    this.dialogRef.close('no');
  }

  onApprove() {
    this.dialogRef.close('yes');
  }

  ngOnInit(): void {
  }
}
