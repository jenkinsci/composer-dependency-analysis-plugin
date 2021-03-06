package de.sandritter.version_analysis_of_build_dependencies.DependencyConfiguration.Mapping.Interface;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sandritter.version_analysis_of_build_dependencies.Domain.Model.Result.Database.ComponentSummary;
import de.sandritter.version_analysis_of_build_dependencies.Domain.Model.Transfer.Interface.Transferable;

/**
 * DatabaseMapper.java
 * This interface can be implemented from objects that wants to map ResultSets of a database
 * to model objects
 *
 * @author Michael Sandritter
 */
public interface DatabaseMapper {

	/**
	 * is mapping the ResultSet from the database to a map of String, {@link ComponentSummary}.
	 * the key value of the map must be the reference of the loaded {@link ComponentSummary}
	 * 
	 * @param result {@link ResultSet}
	 * @return {@link Transferable}
	 * @throws SQLException in case mapping of component results fails
	 */
	public Transferable mapComponentResults(ResultSet result) throws SQLException;
	
	/**
	 * is mapping the {@link ResultSet} to objects given by {@link Class}
	 * 
	 * @param result {@link ResultSet}
	 * @param cls {@link Class}
	 * @return {@link Transferable}
	 * @throws SQLException in case the mapping of the analysis result fails
	 */
	public Transferable mapResult(ResultSet result, Class<?> cls) throws SQLException;
}
