package com.romero.postulacion.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.commons.beanutils.BeanUtils;

public class ResultSetToObject {

    private ResultSet data;

    public ResultSetToObject() {
    }

    public ResultSetToObject(ResultSet data) {
        this.data = data;
    }

    public <T> List<T> mapToList(Class<T> outputClass) {
        List<T> outputList = new ArrayList();
        if (this.data == null) {
            return outputList;
        }
        if (!outputClass.isAnnotationPresent(Entity.class)) {
        }
        try {
            ResultSetMetaData rsmd = this.data.getMetaData();
            Field[] fields = outputClass.getDeclaredFields();
            while (this.data.next()) {
                T bean = outputClass.newInstance();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String rsColumnName = rsmd.getColumnLabel(i);
                    Object rsColumnValue = this.data.getObject(i);
                    for (Field field : fields) {
                        String beanColumnName = field.getName();
                        String AnnotationColumnName = "";

                        if (field.isAnnotationPresent(Column.class)) {
                            Column column = (Column) field.getAnnotation(Column.class);
                            if (!column.name().isEmpty()) {
                                AnnotationColumnName = column.name();
                            }
                        }
                        if (AnnotationColumnName.equalsIgnoreCase(rsColumnName) || beanColumnName.equalsIgnoreCase(rsColumnName)) {                           
                            BeanUtils.setProperty(bean, field.getName(), rsColumnValue);
                            break;
                        }
                    }
                }
                outputList.add(bean);
            }
            this.data.close();
        } catch (IllegalAccessException | InstantiationException | SecurityException | SQLException e) {
            String error = "asda";
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ResultSetToObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outputList;
    }

    public <T> T mapToSingle(Class<T> outputClass) {
        List<T> outputList = mapToList(outputClass);
        if (outputList.size() <= 1) {
            if (outputList.isEmpty()) {
                return null;
            }
        }
        return (T) outputList.get(0);
    }

    public void setData(ResultSet data) {
        this.data = data;
    }

}
