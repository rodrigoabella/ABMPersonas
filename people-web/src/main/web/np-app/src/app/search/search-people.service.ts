import { Injectable } from '@angular/core';
import { PersonFilterForm }    from './person-filter-form';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class SearchPeopleService {
   	private messageSource = new BehaviorSubject('default message');
  	currentMessage = this.messageSource.asObservable();

   constructor(private http: HttpClient) { }

   	changeMessage(message: string) {
    	this.messageSource.next(message)
  	}

   searchPeople(personFilterForm) {
  		let docType = personFilterForm.docType == 'TODAS' ? '' : personFilterForm.docType; 	
   		return this.http.get('app/restApi/personas?' + 'perNombre=' + personFilterForm.name + '&' +'perTipoDocumento=' + docType + '&' + 'pageSize=1000');
   }

   deletePerson(id) {
	   	let httpOptions = {
	    	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
		};
   		return this.http.delete('app/restApi/personas/' + id, httpOptions);
   }

   createPerson(person) {
   		let newPerson = {
							"perNombre": person.name,
							"perApellido": person.lastName,
						    "perFechaNacimiento": person.date,
							"perNumeroDocumento" : person.doc,
						    "perTipoDocumento" : person.docType
						};
   		return this.http.post('app/restApi/personas', newPerson);
   }

   updatePerson(person) {
   		let updatePerson = {
							"perNombre": person.name,
							"perApellido": person.lastName,
						    "perFechaNacimiento": person.date,
							"perNumeroDocumento" : person.doc,
						    "perTipoDocumento" : person.docType
						};
   		return this.http.put('app/restApi/personas/' + person.id, updatePerson);
   }
}