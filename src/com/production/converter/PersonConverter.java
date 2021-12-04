package com.production.converter;
import org.bson.types.ObjectId;

import com.production.domainmodel.Person;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
public class PersonConverter 
{
	// convert Person Object to MongoDB DBObject
	// take special note of converting id String to ObjectId
	public static DBObject toDBObject(Person p) 
	{
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append("name_intrinsisch", p.getName()).append("country_intrinsisch",
						p.getCountry());
		if (p.getId() != null)
			builder = builder.append("_id", new ObjectId(p.getId()));
		return builder.get();
	}

	// convert DBObject Object to Person
	// take special note of converting ObjectId to String
	public static Person toPerson(DBObject doc) 
	{
		Person p = new Person();
		p.setName((String) doc.get("name_intrinsisch"));
		p.setCountry((String) doc.get("country_intrinsisch"));
		ObjectId id = (ObjectId) doc.get("_id");
		p.setId(id.toString());
		return p;
	}
}
