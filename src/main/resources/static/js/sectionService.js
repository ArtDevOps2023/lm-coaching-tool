

const sectionService = () => {
	
	
	const fetchSectionBySectionId = (sectionId) => {
		
		const requestData = JSON.stringify({ "query": `{
												sectionBySectionId( sectionId : ${sectionId}) {
										    		sectionId
										   			name
										    		color
										    		subsectionList {
														subsectionId
											   			description
											    		facilitator
											    		details
											    		targetDay
											    		targetSprint
													}
									  			}
											}`});
	 
		return $.post( { url : '/graphql', data: requestData, contentType: 'application/json' });
	}
	
	const fetchAllSections = () => {
		
		const requestData = JSON.stringify({ "query": `{
												section {
										    		sectionId
										   			name
										    		color
										    		colorDescription
									  			}
											}`});
	 
		return $.post( { url : '/graphql', data: requestData, contentType: 'application/json' });
	}
	
	const upsertSection = async (form,sectionId) => {
		
		const formData = new FormData(form);
	    
		const sectionForm = {
			name: formData.get("name"),
			color: formData.get("color")
		}
		
		const requestData = JSON.stringify({ query : ` mutation {
												  upsertSection( form : {
													  sectionId: "${sectionId}",
													  name: "${sectionForm.name}",
													  color: "${sectionForm.color}"
												  }) 
												  {
												    sectionId
												    name
												    color
											  	  }
											}`});
		
		return await $.post( { url : '/graphql', data: requestData, contentType: 'application/json' });
		
	}
	
	const deleteSection = async (sectionId) => {
		return await $.post( 
				{ 	url : '/graphql', 
					data : JSON.stringify({ query: `mutation { deleteSection(sectionId : ${sectionId}) }`}),
					contentType: 'application/json'
				});
		
		
	}
	
	return {
		fetchSectionBySectionId: fetchSectionBySectionId,
		fetchAllSections : fetchAllSections,
		upsert : upsertSection,
		delete: deleteSection
	}
	
}