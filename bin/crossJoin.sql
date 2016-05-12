SELECT stand.component_name component_name, stand.reference reference, stand.version version, F.build_id build_id, F.job_name job_name, F.maxTime time_stamp, F.build_number build_number FROM stand INNER JOIN (SELECT * FROM dependency INNER JOIN (SELECT MAX(time_stamp) maxTime, build.job_name job_name, build.id build_id, build.build_number build_number FROM build INNER JOIN (SELECT dependency.build_id FROM dependency NATURAL JOIN stand WHERE stand.component_name='Kunde'AND dependency.type='direct') AS D ON build.id=D.build_id GROUP BY build.job_name ) AS E ON E.build_id=dependency.build_id WHERE dependency.type='main') AS F ON stand.reference=F.reference
SELECT stand.component_name component_name, stand.reference reference, stand.version version, F.build_id build_id, F.job_name job_name, F.maxTime time_stamp, F.build_number build_number FROM stand INNER JOIN (SELECT * FROM dependency INNER JOIN (SELECT MAX(time_stamp) maxTime, build.job_name job_name, build.id build_id, build.build_number build_number FROM build INNER JOIN (SELECT dependency.build_id FROM dependency NATURAL JOIN stand WHERE stand.component_name='Kunde' AND dependency.type='direct' ) AS D ON build.id=D.build_id GROUP BY build.job_name ) AS E ON E.build_id=dependency.build_id WHERE dependency.type='main' ) AS F
