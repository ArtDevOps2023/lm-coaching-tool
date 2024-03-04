

const appService = () => {
	
	const upsertEmployee = async (form) => {
		
		const formData = new FormData(form);
		const contacts = [];
		const addresses = [];
		
		form.querySelectorAll('.fieldContactId').forEach( (el, i) => {
	          const contact = {
	          	  id : form.querySelector(`[name="contacts[${i}].id"`).value,
	              contactNo : form.querySelector(`[name="contacts[${i}].contactNo"`).value,
	              isPrimary : form.querySelector(`[name="contacts[${i}].isPrimary"`).checked,
	          };
	          
	          contacts.push(`{ id : "${contact.id}", contactNo: "${contact.contactNo}", isPrimary: ${contact.isPrimary} }`);
	    });
	    
	    form.querySelectorAll('.fieldAddressId').forEach( (el, i) => {
	          const address = {
	          	  id : form.querySelector(`[name="addresses[${i}].id"`).value,
	              address1 : form.querySelector(`[name="addresses[${i}].address1"`).value,
	              address2 : form.querySelector(`[name="addresses[${i}].address2"`).value,
	              isPrimary : form.querySelector(`[name="addresses[${i}].isPrimary"`).checked,
	          };
	          
	          addresses.push(`{ id: "${address.id}", address1: "${address.address1}", address2: "${address.address2}", isPrimary: ${address.isPrimary} }`);
	    })
	    
		const employeeForm = {
			id : formData.get("id"),
			firstName: formData.get("firstName"),
			lastName: formData.get("lastName"),
			middleName: formData.get("middleName"),
			birthdate: formData.get("birthdate"),
			gender: formData.get("gender"),
			maritalStatus: formData.get("maritalStatus"),
			companyPosition: formData.get("companyPosition"),
			dateHired: formData.get("dateHired")
		}
		
		const qryContacts = `[ ` + contacts.join(',') + ` ]`;
	    const qryAddresses = `[ ` + addresses.join(',') + ` ]`;
		
		
		const requestData = JSON.stringify({ query : ` mutation {
												  upsertEmployee( form : {
													  id: "${employeeForm.id}",
													  firstName: "${employeeForm.firstName}",
													  lastName: "${employeeForm.lastName}",
													  middleName: "${employeeForm.middleName}",
													  birthdate: "${employeeForm.birthdate}",
													  gender: "${employeeForm.gender}",
													  maritalStatus: "${employeeForm.maritalStatus}",
													  companyPosition: "${employeeForm.companyPosition}",
													  dateHired: "${employeeForm.dateHired}",
													  contacts: ${qryContacts},
													  addresses: ${qryAddresses}
												  }) 
												  {
												    id
												    firstName
												    lastName
												    middleName
												    birthdate
												    gender
												    maritalStatus
												    companyPosition
												    dateHired
												    contacts { 
												    	id
												      contactNo
												      isPrimary
												    }
												    addresses { 
												    	id
												      address1
												      address2
												      isPrimary
												    }
											  }
											}`});
		
		return await $.post( { url : '/graphql', data: requestData, contentType: 'application/json' });
		
	}
	
	const deleteEmployee = async (id) => {
		return await $.post( 
				{ 	url : '/graphql', 
					data : JSON.stringify({ query: `mutation { deleteEmployee(id : ${id}) }`}),
					contentType: 'application/json'
				});
		
		
	}
	
	return {
		
		upsert : upsertEmployee,
		delete: deleteEmployee
	}
	
}