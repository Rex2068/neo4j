/**
 * Copyright (c) 2002-2014 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.cypher.internal.compiler.v2_1.pipes

import org.neo4j.cypher.internal.compiler.v2_1._
import symbols.{SymbolTable, CypherType}
import collection.Map
import org.scalatest.mock.MockitoSugar

class FakePipe(val data: Iterator[Map[String, Any]], identifiers: (String, CypherType)*) extends Pipe with MockitoSugar {

  def this(data: Traversable[Map[String, Any]], identifiers: (String, CypherType)*) = this(data.toIterator, identifiers:_*)

  val symbols: SymbolTable = SymbolTable(identifiers.toMap)

  def internalCreateResults(state: QueryState) = data.map(m => ExecutionContext(collection.mutable.Map(m.toSeq: _*)))

  def executionPlanDescription = PlanDescription(this, "Fake")

  def exists(pred: Pipe => Boolean) = ???

  val monitor: PipeMonitor = mock[PipeMonitor]
}
