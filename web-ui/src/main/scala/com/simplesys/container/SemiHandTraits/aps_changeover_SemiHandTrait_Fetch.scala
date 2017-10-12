// This file is generated automatically (at 11.10.2017 10:12:14), do not spend any changes here, because they will be lost. Generator: "GenBOContainer, stage: #765"

package ru.simplesys.defs.app.scala.container.aps

import com.simplesys.app.SessionContextSupport
import com.simplesys.isc.system.ServletActorDyn
import com.simplesys.isc.dataBinging.{DSRequestDyn, DSResponseDyn, DSResponseFailureExDyn}
import com.simplesys.common.Strings._
import com.simplesys.jdbc.control.clob._
import akka.actor.Actor
import com.simplesys.isc.dataBinging.RPC.RPCResponseDyn
import com.simplesys.isc.dataBinging.dataSource.RecordDyn
import com.simplesys.isc.grids.RecordsDynList
import com.simplesys.jdbc.control.DSRequest
import com.simplesys.servlet.GetData
import com.simplesys.tuple.{TupleSS10, TupleSS8}
import ru.simplesys.defs.bo.aps._

import scalaz.{Failure, Success}


trait aps_changeover_SemiHandTrait_Fetch extends SessionContextSupport with ServletActorDyn {

    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!! DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////
    val requestData = new DSRequestDyn(request)

    logger debug s"Request for Fetch: ${newLine + requestData.toPrettyString}"

    val dataSet = ChangeoverDS(oraclePool)
    /////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!! END DON'T MOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///////////////////////////////

    def receiveBase: Option[Actor.Receive] = Some(
        {
            case GetData => {
                logger debug s"request: ${newLine + requestData.toPrettyString}"

                val data = requestData.Data
                logger debug s"data: ${newLine + data.toPrettyString}"

                val _data = RecordsDynList()
                val qty: Int = requestData.EndRow.toInt - requestData.StartRow.toInt + 1

                val select = dataSet.Fetch(dsRequest = DSRequest(sqlDialect = sessionContext.getSQLDialect, startRow = requestData.StartRow, endRow = requestData.EndRow, sortBy = requestData.SortBy, data = data, textMatchStyle = requestData.TextMatchStyle.toString))
                
                Out(classDyn = select.result match {
                    case Success(list) => {
                        list foreach {
                            case TupleSS10(durationChangeover: Double, from_typeChangeover: Long, idrcChangeover: Long, to_typeChangeover: Long, id_operstypeOpers_type_From_type: Long, code_operstypeOpers_type_From_type: String, id_rcRc_Idrc: Long, scode_rcRc_Idrc: String, id_operstypeOpers_type_To_type: Long, code_operstypeOpers_type_To_type: String) =>
                                _data += RecordDyn("duration" -> durationChangeover, "idrc" -> idrcChangeover, "from_type" -> from_typeChangeover, "to_type" -> to_typeChangeover, "scode_rc" -> scode_rcRc_Idrc, "code_operstype" -> code_operstypeOpers_type, "code_operstype" -> code_operstypeOpers_type)
                            case x =>
                                new RuntimeException(s"mached as : $x")
                        }

                        logger debug s"_data: ${newLine + _data.toPrettyString}"


                        new DSResponseDyn {
                            Status = RPCResponseDyn.statusSuccess
                            Data = _data
                            TotalRows = requestData.StartRow.toInt + (if (qty == list.length) qty * 2 else list.length)
                        }
                    }
                    case Failure(_) =>
                        new DSResponseFailureExDyn(select)
                })

                selfStop()
            }
            case x =>
                throw new RuntimeException(s"Bad branch $x")
        }
    )

    def wrapperBlobGetter(blob: Blob): String = blob.asString
}
