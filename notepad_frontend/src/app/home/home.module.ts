import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HomeComponent} from './home.component';
import {MaterialModule} from '../material';
import {FormsModule} from '@angular/forms';
import {ModalAddNodeComponent} from './components/modal-add-node/modal-add-node.component';
import {ModalConfirmationComponent} from './components/modal-confiramtion/modal-confirmation.component';

@NgModule({
  declarations: [HomeComponent, ModalAddNodeComponent, ModalConfirmationComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule
  ],
  entryComponents: [ModalAddNodeComponent, ModalConfirmationComponent]
})
export class HomeModule {
}
