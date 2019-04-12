import { Component, Input } from '@angular/core';
import { SearchPeopleService } from './search-people.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-content',
  template: `
    <div class="modal-header">
      <h4 class="modal-title">Eliminar Persona!</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <p>Esta seguro que quiere eliminar a la persona: {{persona.perNombre}} {{persona.perApellido}}?</p>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-danger" (click)="deletePerson(persona.perId)" >Eliminar</button>
    </div>
  `
})
export class NgbdModalContent {
  @Input() persona;

  constructor(private searchPeopleService: SearchPeopleService, public activeModal: NgbActiveModal) {}

  deletePerson(id) {
    this.searchPeopleService.deletePerson(id).subscribe(
        (data: any) => {
          this.activeModal.dismiss();
          this.searchPeopleService.changeMessage("person deleted");
        },
        (err: any) => {
          this.activeModal.dismiss();
          this.searchPeopleService.changeMessage("person deleted");
        }
      );
  }
}