package com.production.dao;


import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.production.converter.PersonConverter;
//inject model in dao
import com.production.domainmodel.Person;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
public class MongoDBPersonDAO 
{
	private DBCollection col;
	
	//pass the MongoClient to the dao constructor
	public MongoDBPersonDAO(MongoClient mongo) 
	{
		this.col = mongo.getDB("journaldev").getCollection("Persons");
	}

	public Person createPerson(Person p) 
	{
		DBObject doc = PersonConverter.toDBObject(p);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		p.setId(id.toString());
		return p;
	}

	public void updatePerson(Person p) 
	{
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		this.col.update(query, PersonConverter.toDBObject(p));
	}

	public List<Person> readAllPerson() 
	{
		List<Person> data = new ArrayList<Person>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) 
		{
			DBObject doc = cursor.next();
			Person p = PersonConverter.toPerson(doc);
			data.add(p);
		}
		return data;
	}

	public void deletePerson(Person p) 
	{
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		this.col.remove(query);
	}

	public Person readPerson(Person p) 
	{
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		DBObject data = this.col.findOne(query);
		return PersonConverter.toPerson(data);
	}
}
