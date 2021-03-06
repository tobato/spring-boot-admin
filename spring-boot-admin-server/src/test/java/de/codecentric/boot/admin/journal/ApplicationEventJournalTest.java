/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.codecentric.boot.admin.journal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

import de.codecentric.boot.admin.event.ClientApplicationRegisteredEvent;
import de.codecentric.boot.admin.journal.ApplicationEventJournal;
import de.codecentric.boot.admin.journal.JournaledEvent;
import de.codecentric.boot.admin.journal.store.SimpleJournaledEventStore;
import de.codecentric.boot.admin.model.Application;

public class ApplicationEventJournalTest {
	private ApplicationEventJournal journal = new ApplicationEventJournal(
			new SimpleJournaledEventStore());

	@Test
	public void test_registration() {
		journal.onApplicationEvent(new ClientApplicationRegisteredEvent(
				new Object(), Application.create("foo").withId("bar").build()));

		Collection<JournaledEvent> events = journal.getEvents();
		assertThat(events.size(), is(1));

		JournaledEvent event = events.iterator().next();
		assertThat(event.getType(), is(JournaledEvent.Type.REGISTRATION));
	}

}
