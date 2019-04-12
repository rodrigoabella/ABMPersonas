import { Component, Input } from '@angular/core';
import { SearchPeopleService } from './search-people.service';
import { PersonFilterForm }    from './person-filter-form';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbdModalContent } from './delete-person-modal.component';
import {Router} from "@angular/router"

@Component({
  selector: 'search-people-module',
  templateUrl: './search-people.component.html',
  styleUrls: ['./search-people.component.css']
})
export class SearchPeopleComponent {
  people = [];
  msg;

  docTypes = ['TODAS', 'DNI', 'CEDULA', 'PASAPORTE'];
 
  personFilter = new PersonFilterForm('', 'TODAS');
 
  submitted = false;
 
  onSubmit() { 
    this.submitted = true;
    this.getPeople();
  }

  constructor(private searchPeopleService: SearchPeopleService, private modalService: NgbModal, private router: Router) {}

  ngOnInit() {
    this.searchPeopleService.currentMessage.subscribe(message => {
      if(message == "person deleted") {
        this.getPeople();
      }
    });
  }

  getPeople() {
    this.searchPeopleService.searchPeople(this.personFilter).subscribe(
      (data: any) => {
        this.people = data.personas;
      },
      (err: any) => {
        //this.msg = err.error.message;
        this.people = [{"perId":7,"perNombre":"flavio","perApellido":"abella","perFechaNacimiento":"2017-11-14","perNumeroDocumento":35259976,"perTipoDocumento":"DNI"},{"perId":8,"perNombre":"rodrigo2","perApellido":"abella","perFechaNacimiento":"2017-11-14","perNumeroDocumento":35259976,"perTipoDocumento":"DNI"},{"perId":9,"perNombre":"rodriGo3","perApellido":"abella","perFechaNacimiento":"2017-11-14","perNumeroDocumento":35259976,"perTipoDocumento":"DNI"},{"perId":10,"perNombre":"flavio4","perApellido":"abella","perFechaNacimiento":"2017-11-14","perNumeroDocumento":35259976,"perTipoDocumento":"CEDULA"},{"perId":11,"perNombre":"flavio5","perApellido":"abella","perFechaNacimiento":"2017-11-14","perNumeroDocumento":35259976,"perTipoDocumento":"CEDULA"},{"perId":12,"perNombre":"rodrigo","perApellido":"abella","perFechaNacimiento":"2017-11-14","perNumeroDocumento":35259976,"perTipoDocumento":"DNI"},{"perId":13,"perNombre":"flavio5","perApellido":"abella","perFechaNacimiento":"2017-11-14","perNumeroDocumento":35259976,"perTipoDocumento":"DNI"},{"perId":15,"perNombre":"rodrigoooo","perApellido":"abella","perFechaNacimiento":"2017-11-14","perNumeroDocumento":35259976,"perTipoDocumento":"CEDULA"}];
      }
    );
  }

  openModal(persona) {
    const modalRef = this.modalService.open(NgbdModalContent);
    modalRef.componentInstance.persona = persona;
  }

  redirectToPerson(persona) {
    let navigationExtras = {
            queryParams: persona
        };
    this.router.navigate(['/app/people'], navigationExtras);

  }
}
