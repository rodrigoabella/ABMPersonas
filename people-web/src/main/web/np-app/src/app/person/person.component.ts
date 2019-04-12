import { Component } from '@angular/core';
import { Person }    from './person';
import { SearchPeopleService } from './../search/search-people.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'person-module',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent {

  docTypes = ['DNI', 'CEDULA', 'PASAPORTE'];
 
  person = new Person(null, '', '', null, 'DNI', '');

  msg;

  constructor(private location: Location, private searchPeopleService: SearchPeopleService, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
        this.person.id = params["perId"];
        this.person.name = params["perNombre"];
        this.person.lastName = params["perApellido"];
        this.person.date = params["perFechaNacimiento"];
        this.person.doc = params["perNumeroDocumento"];
        this.person.docType = params["perTipoDocumento"];
    });
  }

  ngOnInit() { }

  onSubmit() { 
    this.searchPeopleService.createPerson(this.person).subscribe(
      (data: any) => {
        this.location.back();
      },
      (err: any) => {
        this.location.back();
      }
    );
  }

  updatePerson() {
    this.searchPeopleService.updatePerson(this.person).subscribe(
      (data: any) => {
        this.location.back();
      },
      (err: any) => {
        this.location.back();
      }
    );
  }

  back() {
    this.location.back();
  }
}
