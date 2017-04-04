package com.ms.qaTools.toolkit

import java.io.ByteArrayOutputStream
import java.io.PrintStream



trait Status {
  def passed: Boolean
  def failed: Boolean
  def notRun: Boolean
}

case class Passed() extends Status {
  def passed: Boolean = true
  def failed: Boolean = false
  def notRun: Boolean = false
}

case class Failed() extends Status {
  def passed: Boolean = false
  def failed: Boolean = true
  def notRun: Boolean = false
}

case class NotRun() extends Status {
  def passed: Boolean = false
  def failed: Boolean = false
  def notRun: Boolean = true
}

case class Timings(startDate: Option[Long] = None, endDate: Option[Long] = None) {
  def start = Timings(Some(System.currentTimeMillis()))
  def stop = Timings(startDate, Some(System.currentTimeMillis()))

  def duration: Option[Long] = (startDate, endDate) match {
    case (Some(s), Some(e)) => Option(e - s)
    case _ => None
  }
}

trait Result {
  def status: Status = NotRun()
  def passed: Boolean = status.passed
  def failed: Boolean = status.failed
  def notRun: Boolean = status.notRun
  def finished: Boolean = !notRun

  def timings: Timings = Timings()
  def duration = timings.duration

  val exception: Option[Throwable] = None
  val errorMessage: Option[String] = None
}

object Pass {
  def apply(e: Option[Throwable] = None): Result = new Result() {
    override val status: Status = Passed()
    override val exception: Option[Throwable] = e
  }
  def unapply(r: Result): Boolean = {
    r.passed
  }
}

object Fail {
  def apply(e: Option[Throwable] = None): Result = new Result() {
    override val status: Status = Failed()
    override val exception: Option[Throwable] = e
  }

  def unapply(r: Result): Boolean = r.failed
}

object NotRan {
  def unapply(r: Result): Boolean = r.notRun
}
/*
COPYRIGHT NOTICE AND DISCLAIMER
Copyright (c) 2009-2016, Contributor

This program is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License, version 3, as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License, version 3 for more details.

You should have received a copy of the GNU Lesser General Public License, version 3, along with this program; if not, see http://www.gnu.org/licenses/ or write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
THE FOLLOWING DISCLAIMER APPLIES TO ALL SOFTWARE CODE AND OTHER MATERIALS CONTRIBUTED IN CONNECTION WITH THIS PROGRAM:
THIS SOFTWARE IS LICENSED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE AND ANY WARRANTY OF NON-INFRINGEMENT, ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. THIS SOFTWARE MAY BE REDISTRIBUTED TO OTHERS ONLY BY EFFECTIVELY USING THIS OR ANOTHER EQUIVALENT DISCLAIMER AS WELL AS ANY OTHER LICENSE TERMS THAT MAY APPLY.
*/