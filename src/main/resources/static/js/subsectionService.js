

const subsectionService = () => {
	
	const upsertSubsection = async (form, subsectionId) => {
		
		const formData = new FormData(form);
	    
		const subsectionForm = {
			subsectionId : subsectionId,
			sectionId: formData.get("sectionId"),
			description: formData.get("description"),
			facilitator: formData.get("facilitator"),
			details: formData.get("details"),
			targetDay: formData.get("targetDay"),
			targetSprint: formData.get("targetSprint")
			
		}
		
		const requestData = JSON.stringify({ query : ` mutation {
												  upsertSubsection( form : {
													  subsectionId: "${subsectionForm.subsectionId}",
													  sectionId: "${subsectionForm.sectionId}",
													  description: "${subsectionForm.description}",
													  facilitator: "${subsectionForm.facilitator}",
													  details: "${subsectionForm.details}",
													  targetDay: "${subsectionForm.targetDay}",
													  targetSprint: "${subsectionForm.targetSprint}",
												  }) 
												  {
												    subsectionId
												    sectionId
												    description
												    facilitator
												    details
												    targetDay
												    targetSprint
											  	  }
											}`});
		
		return await $.post( { url : '/graphql', data: requestData, contentType: 'application/json' });
		
	}
	
	const deleteSubsection = async (subsectionId) => {
		return await $.post( 
				{ 	url : '/graphql', 
					data : JSON.stringify({ query: `mutation { deleteSubsection(subsectionId : ${subsectionId}) }`}),
					contentType: 'application/json'
				});
	}
	
	return {
		upsert : upsertSubsection,
		delete: deleteSubsection
	}
	
}