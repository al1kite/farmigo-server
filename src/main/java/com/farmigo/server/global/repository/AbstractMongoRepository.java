package com.farmigo.server.global.repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.index.IndexDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * @Description : AbstractMongoRepository
 */

@Slf4j
public abstract class AbstractMongoRepository<T> {


    protected abstract MongoTemplate getMongoTemplate();

    protected abstract Class<T> getDataType();

    private Class<T> dataType;

    @PostConstruct
    private void init() {
        this.dataType = getDataType();
    }

    public List<T> findAll() {
        return this.getMongoTemplate().findAll(this.dataType);
    }

    public List<T> findAll(String collectionName) {
        return this.getMongoTemplate().findAll(this.dataType, collectionName);
    }

    public List<T> find(Query query) {
        return this.getMongoTemplate().find(query, this.dataType);
    }

    protected List<T> find(Query query, String collectionName) {
        return this.getMongoTemplate().find(query, this.dataType, collectionName);
    }

    public T findById(String id) {
        return this.getMongoTemplate().findById(id, this.dataType);
    }

    public T findOne(Query query) {
        return this.getMongoTemplate().findOne(query, this.dataType);
    }

    public T findOne(Query query, String collectionName) {
        return this.getMongoTemplate().findOne(query, this.dataType, collectionName);
    }

    public Long count(Query query) {
        return this.getMongoTemplate().count(query, this.dataType);
    }

    public T insert(T objectToSave) {
        return this.getMongoTemplate().insert(objectToSave);
    }

    protected T insert(T objectToSave, String collectionName) {
        return this.getMongoTemplate().insert(objectToSave, collectionName);
    }

    public Collection<T> insertAll(Collection<T> batchToSave) {
        return this.getMongoTemplate().insert(batchToSave, this.dataType);
    }

    public Collection<T> insertAll(Collection<T> batchToSave, String collectionName) {
        return this.getMongoTemplate().insert(batchToSave, collectionName);
    }

    public T save(T objectToSave) {
        return this.getMongoTemplate().save(objectToSave);
    }

    protected T save(T objectToSave, String collectionName) {
        return this.getMongoTemplate().save(objectToSave, collectionName);
    }

    public UpdateResult upsert(Query query, Update update) {
        return this.getMongoTemplate().upsert(query, update, this.dataType);
    }

    public UpdateResult updateFirst(Query query, Update update) {
        return this.getMongoTemplate().updateFirst(query, update, this.dataType);
    }

    public UpdateResult updateMulti(Query query, Update update) {
        return this.getMongoTemplate().updateMulti(query, update, this.dataType);
    }

    public T findAndModify(Query query, Update update, FindAndModifyOptions options) {
        return this.getMongoTemplate().findAndModify(query, update, options, this.dataType);
    }

    public <O> AggregationResults<O> aggregate(Aggregation agg, Class<O> outputType) {
        return this.getMongoTemplate().aggregate(agg, this.dataType.getSimpleName(), outputType);
    }

    public DeleteResult remove(T object) {
        return this.getMongoTemplate().remove(object);
    }

    public DeleteResult remove(Query query) {
        return this.getMongoTemplate().remove(query, this.dataType);
    }
    public boolean exists(Query query) {
        return this.getMongoTemplate().exists(query, this.dataType);
    }

    public void ensureIndex(String collectionName, IndexDefinition index) {
        this.getMongoTemplate().indexOps(collectionName).ensureIndex(index);
    }
}
