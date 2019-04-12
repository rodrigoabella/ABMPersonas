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
        this.msg = err.error.message;
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
